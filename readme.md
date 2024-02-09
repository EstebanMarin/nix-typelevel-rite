# Rock the JVM typelevel rite

code_dir = 'path_to_your_code_directory'


# Initialize an empty string to hold the README content

readme_content = ''

# Walk through the directory

for root, dirs, files in os.walk(code_dir):
    for file in files:
        # Check if the file has one of the desired extensions
        if any(file.endswith(ft) for ft in file_types):
            # Open the file and read its content
            with open(os.path.join(root, file), 'r') as f:
                code_content = f.read()

            # Add the file name and code content to the README content
            readme_content += f'## {file}\n```{file.split(".")[-1]}\n{code_content}\n```\n'

# Write the README content to a new README file

with open('README.md', 'w') as f:
    f.write(readme_content)
